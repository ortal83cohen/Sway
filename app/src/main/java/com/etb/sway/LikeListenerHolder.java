package com.etb.sway;

import com.etb.sway.common.data.MapPoiInterface;
import com.etb.sway.model.CardModel;
import com.etb.sway.model.Likes;

public  interface LikeListenerHolder{
    void onLike(int id);
    void onDislike(int id);
    void onUndo(int id);
    void addLikeItem(MapPoiInterface cardModel);
    void addDisLikeItem(MapPoiInterface cardModel);
    Likes getLikes();
}
