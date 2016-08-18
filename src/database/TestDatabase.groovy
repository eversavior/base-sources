package database

import groovy.sql.Sql;

class TestDatabase {

    TestDatabase() {


        def db = [url: "jdbc:mysql://104.131.161.78/?useUnicode=true&characterEncoding=UTF-8",
                  user:"ROOT_REMOTE3", password:"Nnn09011666", driver: 'com.mysql.jdbc.Driver']

        def sql = Sql.newInstance(db.url, db.user, db.password)

        println "DB connection ready"


        sql.eachRow('''SELECT * FROM monopoly.games where player0=19968 || player1=19968 || player2=19968 || player3=19968 || player4=19968;''', { game ->
            println game.uid
        })
    }
}
