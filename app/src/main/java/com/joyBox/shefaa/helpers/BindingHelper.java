package com.joyBox.shefaa.helpers;

import android.content.Context;
import android.widget.ImageView;

import com.JoyBox.Shefaa.R;
import com.joyBox.shefaa.configurations.GlideApp;
import com.joyBox.shefaa.entities.MagazinePost;
import com.joyBox.shefaa.enums.ImageSize;
import com.joyBox.shefaa.networking.NetworkingHelper;

public class BindingHelper {

    public static void loadPostImage(ImageView imageView, ImageSize imageSize, MagazinePost magazinePost) {

        Context context = imageView.getContext();
        String url = NetworkingHelper.MagazinePostPrefixUrl + imageSize.getSize()
                + NetworkingHelper.MagazinePostSuffixUrl + magazinePost.getImage_Name();
        GlideApp.with(context).load(url).placeholder(R.drawable.user)
                .error(R.drawable.user).into(imageView);

    }
}
