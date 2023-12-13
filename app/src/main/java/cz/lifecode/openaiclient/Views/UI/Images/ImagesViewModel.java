package cz.lifecode.openaiclient.Views.UI.Images;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class ImagesViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public ImagesViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is images fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}