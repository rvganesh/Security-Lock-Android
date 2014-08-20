package softwareinclude;

import de.greenrobot.daogenerator.Entity;
import de.greenrobot.daogenerator.Schema;

/**
 * Basic schema model @author Octa - Thanks :)
 * Security Lock generate model @author Manolescu Sebastian
 */
public class SecurityLockDaoGenerator {

    private static final String PROJECT_DIR = System.getProperty("user.dir").replace("\\", "/");
    private static final String PROJECT_PATH = "\\app\\src\\main\\java\\softwareinclude\\ro\\securitylockandroid\\model";
    private static final String OUT_DIR = PROJECT_DIR + PROJECT_PATH.replace("\\", "/");

    public static void main(String args[]) throws Exception {
        Schema schema = new Schema(1, "");

        createSchema(schema);

        new de.greenrobot.daogenerator.DaoGenerator().generateAll(schema, OUT_DIR);
    }

    /**
     * Create Schema
     */
    private static void createSchema(Schema schema) {
        Entity accountDataModel = schema.addEntity("AccountDataModel");
        accountDataModel.addIdProperty().primaryKey().autoincrement();
        accountDataModel.addStringProperty("accountName").notNull().notNull();
        accountDataModel.addStringProperty("accountPassword").notNull();
        accountDataModel.addStringProperty("accountDetails").notNull();
    }


}
