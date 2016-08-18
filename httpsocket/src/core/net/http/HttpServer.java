package core.net.http;

import lombok.extern.java.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

@Log
public class HttpServer
{
    private PrintWriter out;
    private HttpBodyParser httpBodyParser = new HttpBodyParser();
    private HttpDataListener listener;
    private ServerSocket socket;

    public HttpServer(int port)
    {
        try {
            socket = new ServerSocket(port);
        } catch (IOException e) {
            System.err.println(e);
            System.err.println("Usage: java HttpMirror <port>");
        }
    }

    public void listen()
    {
        BufferedReader in = null;
        Socket client = null;
        HttpRequestData httpRequestData = null;

        try
        {
            try
            {
                client = socket.accept();

                in = new BufferedReader(new InputStreamReader(client.getInputStream()));
                out = new PrintWriter(client.getOutputStream());
                httpRequestData = httpBodyParser.parser(in);

                if(httpRequestData.path.equals("favicon.ico"))
                    throw new Exception();
            }
            catch (Exception excp)
            {
                writeFailHeader();
                return;
            }
            finally
            {
                if(httpRequestData != null)
                {
                    writeHeader();
                    listener.handleHttpRequest(this, httpRequestData);
                }
                else {
                    writeFailHeader();
                }

                out.close(); // Flush and close the output stream
                in.close(); // Close the input stream
                client.close(); // Close the socket itseln

                out = null;
                in = null;
                client = null;
            }

        }
        catch (IOException e)
        {
            System.err.println(e);
        }
    }

    private void writeFailHeader()
    {
        writeOut("HTTP/1.1 404"); // Version & status code
        writeOut("Content-Type: text/html; charset=utf-8"); // The type of data
        writeOut("Date: " + System.currentTimeMillis()); // The type of data
        writeOut("Connection: close"); // Will close stream
        writeOut(""); // End of headers
    }

    private void writeHeader()
    {
        writeOut("HTTP/1.1 200"); // Version & status code
        writeOut("Content-Type: text/html; charset=utf-8"); // The type of data
        writeOut("Date: " + System.currentTimeMillis()); // The type of data
        writeOut("Connection: close"); // Will close stream
        writeOut(""); // End of headers
    }

    public void writeOut(String string)
    {
        out.print(string + "\r\n");
    }

    public void setListener(HttpDataListener listener) {
        this.listener = listener;
    }

    public HttpDataListener getListener() {
        return listener;
    }
}
