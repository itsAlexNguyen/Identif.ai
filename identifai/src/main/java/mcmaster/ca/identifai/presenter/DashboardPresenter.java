package mcmaster.ca.identifai.presenter;

import android.content.res.Resources;
import android.graphics.BitmapFactory;

import mcmaster.ca.appcore.network.HttpCallback;
import mcmaster.ca.identifai.R;
import mcmaster.ca.text.TextDetectionService;
import mcmaster.ca.text.models.GetCreditsRs;
import mcmaster.ca.text.models.SearchMovieRs;
import mcmaster.mcmaster.ca.image.ImageDetectionService;
import mcmaster.mcmaster.ca.image.models.RetrieveCelebritiesRs;
import okhttp3.Call;

import java.io.IOException;

public class DashboardPresenter {
    public DashboardPresenter(Resources resources) {
        ImageDetectionService service = new ImageDetectionService();
        service.retrieveImageResults(BitmapFactory.decodeResource(resources, R.drawable.celeb),
            new HttpCallback<RetrieveCelebritiesRs>() {
                @Override
                public void onSuccess(Call call, RetrieveCelebritiesRs response) {

                }

                @Override
                public void onFailure(Call call, IOException exception) {

                }
            });

        TextDetectionService textDetectionService = new TextDetectionService();
        textDetectionService.retrieveCreditsForMovie("51497", new HttpCallback<GetCreditsRs>() {
            @Override
            public void onSuccess(Call call, GetCreditsRs response) {

            }

            @Override
            public void onFailure(Call call, IOException exception) {

            }
        });
    }

}
