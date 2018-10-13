package com.joyBox.shefaa.entities.models

/**
 * Created by Adhamkh on 2018-10-05.
 */
class MagazinePostCommentAddModel constructor(userId: String, postId: String, title: String, body: String) {

    var data: String = "{\"uid\":\"" + userId + "\",\n" +
            "  \"nid\":\"" + postId + "\",\n" +
            "  \"subject\":\"" + title + "\",\n" +
            "  \"comment_body\":{\n" +
            "    \"und\":[{\n" +
            "      \"value\":\"" + body + "\"\n" +
            "    }]\n" +
            "  }\n}"


}