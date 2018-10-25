package com.joyBox.shefaa.entities;

import java.util.List;
import java.util.Vector;


public class MagazinePostCommentBody {

    private List<CommentUnit> und = new Vector<>();

    public List<CommentUnit> getUnd() {
        return und;
    }

    public void setUnd(List<CommentUnit> und) {
        this.und = und;
    }

    public class CommentUnit {
        private String value = "";
        private String format = "";
        private String safe_value = "";

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }

        public String getFormat() {
            return format;
        }

        public void setFormat(String format) {
            this.format = format;
        }

        public String getSafe_value() {
            return safe_value;
        }

        public void setSafe_value(String safe_value) {
            this.safe_value = safe_value;
        }
    }
}
