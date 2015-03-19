package com.etb.sway;

import com.etb.sway.common.data.MapPoiInterface;
import com.etb.sway.model.PoiData;

public interface LikeListener {

    void onUndo(MapPoiInterface MapPoi);

    void addLikeItem(MapPoiInterface MapPoi);

    void addDisLikeItem(MapPoiInterface MapPoi);

    PoiData getData();
}
