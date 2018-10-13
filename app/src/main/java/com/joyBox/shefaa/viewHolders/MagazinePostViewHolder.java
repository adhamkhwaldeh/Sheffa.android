package com.joyBox.shefaa.viewHolders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.JoyBox.Shefaa.R;
import com.joyBox.shefaa.entities.MagazinePost;
import com.joyBox.shefaa.enums.ImageSize;
import com.joyBox.shefaa.helpers.BindingHelper;

import org.w3c.dom.Text;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Adhamkh on 2018-08-21.
 */

public class MagazinePostViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.magazinePostImage)
    public ImageView magazinePostImage;

    @BindView(R.id.magazinePostTitle)
    public TextView magazinePostTitle;

    @BindView(R.id.magazinePostTags)
    public TextView magazinePostTags;

    public MagazinePostViewHolder(View view) {
        super(view);
        ButterKnife.bind(this, view);
    }

    public void bind(MagazinePost entity) {
        BindingHelper.loadPostImage(magazinePostImage, ImageSize.Large, entity);
        magazinePostTitle.setText(entity.getTitle());
        magazinePostTags.setText(entity.getTagsString());
    }

}
