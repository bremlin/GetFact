package ru.ibcon.getfact.objects;

import org.json.JSONException;
import org.json.JSONObject;

public class SFProject {
    private int objectId;
    private int parentId;
    private int wbsId;

    private String name;
    private String id;

    public SFProject(JSONObject object) {
        try {
            this.objectId = object.getInt(ProjectHelper.PROJ_ID);
            this.parentId = object.getInt(ProjectHelper.PARENT_ID);
            this.wbsId = object.getInt(ProjectHelper.WBS_ID);
            this.name = object.getString(ProjectHelper.NAME);
            this.id = object.getString(ProjectHelper.ID);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public int getObjectId() {
        return objectId;
    }

    public int getParentId() {
        return parentId;
    }

    public int getWbsId() {
        return wbsId;
    }

    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(ProjectHelper.PROJ_ID).append(objectId).append("\n");
        sb.append(ProjectHelper.PARENT_ID).append(parentId).append("\n");
        sb.append(ProjectHelper.WBS_ID).append(wbsId).append("\n");
        sb.append(ProjectHelper.NAME).append(name).append("\n");
        sb.append(ProjectHelper.ID).append(id).append("\n");
        return sb.toString();
    }
}
