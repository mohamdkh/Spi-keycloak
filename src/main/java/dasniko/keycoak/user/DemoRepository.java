package dasniko.keycoak.user;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Niko Köbler, http://www.n-k.de, @dasniko
 */
class DemoRepository {

    private final List<DemoUser> users;

    DemoRepository() {
        Long created = System.currentTimeMillis();
        users = Arrays.asList(
                new DemoUser("1", "Ali", "Ali", true, created),
                new DemoUser("2", "mohamed", "mohamed", true, created),
                new DemoUser("3", "khafchrait", "khafchrait", true, created),
                new DemoUser("4", "user", "user", true, created),
                new DemoUser("5", "user2", "user2", true, created),
                new DemoUser("6", "user5", "user5", false, created),
                new DemoUser("6", "user66", "user66", false, created)
        );
    }

    List<DemoUser> getAllUsers() {
        return users;
    }

    int getUsersCount() {
        return users.size();
    }

    DemoUser findUserById(String id) {
        return users.stream().filter(user -> user.getId().equals(id)).findFirst().orElse(null);
    }

    DemoUser findUserByUsernameOrEmail(String username) {
        return users.stream()
                .filter(user -> user.getUsername().equalsIgnoreCase(username) || user.getEmail().equalsIgnoreCase(username))
                .findFirst().orElse(null);
    }

    List<DemoUser> findUsers(String query) {
        return users.stream()
                .filter(user -> user.getUsername().contains(query) || user.getEmail().contains(query))
                .collect(Collectors.toList());
    }

    boolean validateCredentials(String username, String password) {
        return findUserByUsernameOrEmail(username).getPassword().equals(password);
    }

    boolean updateCredentials(String username, String password) {
        findUserByUsernameOrEmail(username).setPassword(password);
        return true;
    }

}
