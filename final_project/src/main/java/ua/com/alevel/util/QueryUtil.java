package ua.com.alevel.util;

import ua.com.alevel.persistence.entity.user.Client;

public final class QueryUtil {
    private QueryUtil() {

    }

    public static String getUpdateProfileQuery(Client client) {
        return "update Client set firstName = "
                + client.getFirstName() + ", lastName = "
                + client.getLastName() + ", phoneNumber = "
                + client.getPhoneNumber() + ", email ="
                + client.getEmail() + ", sex = "
                + client.getSex() + ", birthDate = "
                + client.getBirthDate();
    }
}
