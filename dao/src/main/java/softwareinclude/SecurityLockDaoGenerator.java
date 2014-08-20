package softwareinclude;

import de.greenrobot.daogenerator.Schema;

/**
 * Basic schema model @author Octa - Thanks :)
 * Security Lock generate model @author Manolescu Sebastian
 */
public class SecurityLockDaoGenerator {

    private static final String PROJECT_DIR = System.getProperty("user.dir").replace("\\", "/");
    private static final String OUT_DIR = PROJECT_DIR + "app/src/main/java/softwareinclude/ro/securitylockandroid/model";

    public static void main(String args[]) throws Exception {
        Schema schema = new Schema(1, "ro.softwareinclude");

        createSchema(schema);

        new de.greenrobot.daogenerator.DaoGenerator().generateAll(schema, OUT_DIR);
    }

    /**
     * Create Schema
     */
    private static void createSchema(Schema schema) {



    }


}
