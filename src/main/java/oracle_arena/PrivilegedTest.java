package oracle_arena;

import java.security.AccessController;

/**
 * Created by xudabiao on 2017/2/27.
 */
public class PrivilegedTest {
    public static void main(String[] args) {
        AccessController.checkPermission(new RuntimePermission("exit"));
    }
}
