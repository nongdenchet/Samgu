package apidez.com.samgu.viewmodel;

import android.databinding.BaseObservable;
import android.databinding.ObservableArrayList;
import android.databinding.ObservableInt;
import android.databinding.ObservableList;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;
import rx.Scheduler;
import rx.functions.Func1;
import rx.subjects.BehaviorSubject;

/**
 * Created by nongdenchet on 6/4/16.
 */
public class SearchViewModel extends BaseObservable {
    private Scheduler observeOn, subsribeOn;

    public SearchViewModel(Scheduler observeOn, Scheduler subsribeOn) {
        this.observeOn = observeOn;
        this.subsribeOn = subsribeOn;
    }

    public ObservableInt emptyVisibility = new ObservableInt(View.VISIBLE);
    public ObservableList<String> searchResult = new ObservableArrayList<>();
    private BehaviorSubject<String> keywordEvent = BehaviorSubject.create("");

    public void onKeyWordChange(String keyword) {
        keywordEvent.onNext(keyword);
    }

    public Observable<List<String>> bindSearchResult() {
        return keywordEvent.asObservable()
                .flatMap((Func1<String, Observable<List<String>>>) this::filterResult)
                .subscribeOn(subsribeOn)
                .observeOn(observeOn)
                .doOnNext(result -> {
                    searchResult.clear();
                    searchResult.addAll(result);
                    emptyVisibility.set(result.isEmpty() ? View.VISIBLE : View.GONE);
                });
    }

    private Observable<List<String>> filterResult(String keyword) {
        return Observable.from(sources())
                .filter(element -> element.toLowerCase().contains(keyword.toLowerCase()))
                .toList();
    }

    private List<String> sources() {
        ArrayList<String> sources = new ArrayList<>();
        sources.add("Animals");
        sources.add("Back At Your Door");
        sources.add("Beautiful Goodbye");
        sources.add("Better That We Break");
        sources.add("Can't Stop");
        sources.add("Chilly Winter");
        sources.add("Coming Back for You");
        sources.add("Daylight");
        sources.add("Doin' Dirt");
        sources.add("Don't Know Nothing");
        sources.add("Feelings");
        sources.add("Figure It Out");
        sources.add("Fortune Teller");
        sources.add("Get Back In My Life");
        sources.add("Give a Little More");
        sources.add("Goodnight Goodnight");
        sources.add("Hands All Over");
        sources.add("Harder to Breathe");
        sources.add("I Can't Lie");
        sources.add("If I Never See Your Face Again");
        sources.add("In Your Pocket");
        sources.add("Infatuation");
        sources.add("Is Anybody Out There");
        sources.add("It Was Always You");
        sources.add("Just A Feeling");
        sources.add("Kiwi");
        sources.add("Ladykiller");
        sources.add("Last Chance");
        sources.add("Leaving California");
        sources.add("Little Of Your Time");
        sources.add("Losing My Mind");
        sources.add("Secret");
        sources.add("She Will Be Loved");
        return sources;
    }
}
