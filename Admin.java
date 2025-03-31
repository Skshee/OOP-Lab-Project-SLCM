package SLCMProject;

public class Admin {

    static boolean authenticate(String username, String password)
            throws InvalidUsernameException, InvalidPasswordException {
        if (!username.equals("Admin"))
            throw new InvalidUsernameException("Invalid username");
        if (!password.equals("administrator123"))
            throw new InvalidPasswordException("Invalid password");
        return true;
    }

    static void updateAttendance() {

    }
}
