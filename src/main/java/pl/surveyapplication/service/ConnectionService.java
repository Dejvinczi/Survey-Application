package pl.surveyapplication.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.surveyapplication.exception.SurveyNotFoundException;
import pl.surveyapplication.model.Connection;
import pl.surveyapplication.model.User;
import pl.surveyapplication.repository.ConnectionRepository;

import java.util.List;
import java.util.Optional;

/**
 * @author Dawid
 * @version 1.0
 * Klasa serwisów połączeń user - survey.
 * */
@Component
public class ConnectionService {
    /**
     * Zmienna przechowuje repozytorium połączeń user - survey
     * */
    @Autowired
    private ConnectionRepository connectionRepository;

    /**
     * Metoda dodaje połączenie do bazy danych.
     * @param connection połączenie.
     * @return obiekt klasy Connection
     * */
    public Connection addConnection(Connection connection){
        return connectionRepository.save(connection);
    }

    /**
     * Metoda zwraca liste połączeń z naszej bazie danych.
     * @return liste obiektów klasy Connection
     * */
    public List<Connection> getAllConnections(){
        return connectionRepository.findAll();
    }

    /**
     * Metoda zwraca połączenie z naszej bazy danych o konkretnym ID.
     * @param connectionId id konkretnego połączenia.
     * @return obiekt klasy Connection
     * */
    public Connection getConnection(Long connectionId){
        Optional<Connection> optionalConnection = connectionRepository.findById(connectionId);
        if(!optionalConnection.isPresent())
            throw new SurveyNotFoundException("Connection of this ID is not available...");
        return optionalConnection.get();
    }

    /**
     * Metoda zwraca liste połączeń z naszej bazy danych dla danego użytkownika.
     * @param user obiekt User którego dotyczy połączenie.
     * @return liste obiektów klasy Connection
     * */
    public List<Connection> getConnectionsByUserId(User user){
        return connectionRepository.findByUserId(user);
    }

    public void updateConnection(Connection connection){
        Optional<Connection> updateConnection = connectionRepository.findById(connection.getConnectionId());

        if(updateConnection.isPresent()) {
            Connection newConnection = updateConnection.get();
            newConnection.setConnectionId(connection.getConnectionId());
            newConnection.setSurvey(connection.getSurvey());
            newConnection.setUser(connection.getUser());
            newConnection.setDevoted(true);
            connectionRepository.save(newConnection);
        }
    }
}
