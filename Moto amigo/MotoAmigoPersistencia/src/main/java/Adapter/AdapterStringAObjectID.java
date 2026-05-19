package Adapter;

import org.bson.types.ObjectId;

/**
 *
 * @author joset
 */
public class AdapterStringAObjectID {

    private AdapterStringAObjectID() {
    }

    public static ObjectId aObjectId(String id) {
        if (id == null || !ObjectId.isValid(id)) {
            return null;
        }
        return new ObjectId(id);
    }

}
