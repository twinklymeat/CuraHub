package com.curahub.curahub.user;

import java.util.Comparator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.curahub.curahub.appointments.Appointments;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public Object getAllUsers() {
        return userRepository.findAll();
    }

    public User getUserByID(long id) {
        return userRepository.getUserById(id);
    }

    public Object getUserByName(String name) {
        return userRepository.getUserByName(name);
    }

    public User addUser(User user) {
        return userRepository.save(user);
    }

    public void deleteUser(long id) {
        userRepository.deleteById(id);
    }

    public User updateUser(long id, User user) {
        return userRepository.save(user);
    }

    public Object getAppointmentsByUser(long id) {
        return userRepository.getAppointmentsByUser(id);
    }

    public List<Appointments> getUpcomingAppointments(long id) {
        List<Appointments> appts = userRepository.getAppointmentsByUser(id);
        List<Appointments> newAppts = userRepository.getAppointmentsByUser(id);
        int offset = 0;
        for (int i = 0; i < appts.size(); i++) {
            if (appts.get(i).getCompleted() == false) {
                newAppts.remove(i - offset);
                offset += 1;
            }
        };
        newAppts.sort(Comparator.comparing(Appointments::getTime));
        return newAppts;
    }

    public List<Appointments> getPastAppointments(long id) {
        List<Appointments> appts = userRepository.getAppointmentsByUser(id);
        List<Appointments> newAppts = userRepository.getAppointmentsByUser(id);
        int offset = 0;
        for (int i = 0; i < appts.size(); i++) {
            if (appts.get(i).getCompleted() == true) {
                newAppts.remove(i - offset);
                offset += 1;
            }
        };
        newAppts.sort(Comparator.comparing(Appointments::getTime).reversed());
        return newAppts;
    }

    public User getUserByEmail(String email) {
        return userRepository.getUserByEmail(email);
    }

}   
