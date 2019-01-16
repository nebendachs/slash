package de.sharknoon.slash.Image;

import android.widget.ImageView;

import de.sharknoon.slash.People.Person;
import de.sharknoon.slash.R;

public class SentimentLoader {
    public SentimentLoader(Person.Sentiment sentiment, ImageView targetView) {
        if(sentiment != null) {
            switch(sentiment.getPolarity()) {
                case Person.POSITIVE:
                    //targetView.setImageResource(R.drawable.ic_sun_outline);
                    targetView.setImageResource(R.drawable.ic_mood_positive);
                    break;
                case Person.NEUTRAL:
                    //targetView.setImageResource(R.drawable.ic_overcast_outline);
                    targetView.setImageResource(R.drawable.ic_mood_neutral);
                    break;
                case Person.NEGATIVE:
                    //targetView.setImageResource(R.drawable.ic_rain_outline);
                    targetView.setImageResource(R.drawable.ic_mood_negative);
                    break;
            }
        }
    }
}
