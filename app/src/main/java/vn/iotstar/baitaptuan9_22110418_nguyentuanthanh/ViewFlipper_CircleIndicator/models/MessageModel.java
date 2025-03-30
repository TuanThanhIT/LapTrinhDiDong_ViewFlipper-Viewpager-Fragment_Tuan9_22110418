package vn.iotstar.baitaptuan9_22110418_nguyentuanthanh.ViewFlipper_CircleIndicator.models;

import java.io.Serializable;
import java.util.List;

public class MessageModel implements Serializable {
    private boolean success;
    private String message;
    private List<ImagesSlider> result;

    public boolean isSuccess() {
        return success;
    }

    public String getMessage() {
        return message;
    }

    public List<ImagesSlider> getResult() {
        return result;
    }
}
