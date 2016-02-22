package com.github.zzwwws.rxzhihudaily.presenter.usercase;

import com.github.zzwwws.rxzhihudaily.model.entities.StartImage;
import com.github.zzwwws.rxzhihudaily.model.service.ServiceRest;

import rx.Observable;

/**
 * Created by zzw on 16/2/22.
 */
public class SplashCase extends UserCase<StartImage, String> {
    @Override
    protected Observable<StartImage> interactor(String density) {
        return ServiceRest.getInstance().getStartImage(density);
    }
}
