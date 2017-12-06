package ru.ibcon.getfact.objects;

import org.json.JSONException;
import org.json.JSONObject;

public class SFEPS {
    private int id;
    private int parentId;
    private int sequence;

    private String name;
    private String code;
    private String dbName;

    public SFEPS(JSONObject object) {
        try {
            this.id = object.getInt(EPSHelper.ID);
            this.parentId = object.getInt(EPSHelper.PARENT_ID);
            this.sequence = object.getInt(EPSHelper.SEQUENCE);
            this.name = object.getString(EPSHelper.NAME);
            this.code = object.getString(EPSHelper.CODE);
            this.dbName = object.getString(EPSHelper.DB_NAME);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public int getId() {
        return id;
    }

    public int getParentId() {
        return parentId;
    }

    public int getSequence() {
        return sequence;
    }

    public String getName() {
        return name;
    }

    public String getCode() {
        return code;
    }

    public String getDbName() {
        return dbName;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(EPSHelper.ID).append(id).append("\n");
        sb.append(EPSHelper.PARENT_ID).append(parentId).append("\n");
        sb.append(EPSHelper.SEQUENCE).append(sequence).append("\n");
        sb.append(EPSHelper.NAME).append(name).append("\n");
        sb.append(EPSHelper.CODE).append(code).append("\n");
        sb.append(EPSHelper.DB_NAME).append(dbName).append("\n");
        return sb.toString();
    }
}
