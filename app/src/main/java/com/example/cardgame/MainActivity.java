package com.example.cardgame;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class MainActivity extends AppCompatActivity {
//Test
//    int image1, image2, image3, image4, image5, image6;
    Card card1, card2, card3, card4, card5, card6;
    int backImage;
    ImageView[] listOfImageView;
    ArrayList<Card> cardArray = new ArrayList<>();
    int firstCard, secondCard, numCardOver;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        image1 = R.drawable.cow1;
//        image2 = R.drawable.cow2;
//        image3 = R.drawable.cow3;
//        image4 = R.drawable.cow4;
//        image5 = R.drawable.cow5;
//        image6 = R.drawable.cow6;
        backImage = R.drawable.question;
        card1 = new Card(R.drawable.cow1);
        card2 = new Card(R.drawable.cow2);
        card3 = new Card(R.drawable.cow3);
        card4 = new Card(R.drawable.cow4);
        card5 = new Card(R.drawable.cow5);
        card6 = new Card(R.drawable.cow6);

        Card[] cards = {card1, card2, card3, card4, card5, card6};

//        Integer[] imageList = {image1, image2, image3, image4, image5, image6};
        for (int i = 0; i < cards.length; i++){
            cardArray.add(cards[i]);
            cardArray.add(cards[i]);
        }
        Collections.shuffle(cardArray);
        listOfImageView = new ImageView[12];
        listOfImageView[0] = (ImageView) findViewById(R.id.pic1);
        listOfImageView[1] = (ImageView) findViewById(R.id.pic2);
        listOfImageView[2] = (ImageView) findViewById(R.id.pic3);
        listOfImageView[3] = (ImageView) findViewById(R.id.pic4);
        listOfImageView[4] = (ImageView) findViewById(R.id.pic5);
        listOfImageView[5] = (ImageView) findViewById(R.id.pic6);
        listOfImageView[6] = (ImageView) findViewById(R.id.pic7);
        listOfImageView[7] = (ImageView) findViewById(R.id.pic8);
        listOfImageView[8] = (ImageView) findViewById(R.id.pic9);
        listOfImageView[9] = (ImageView) findViewById(R.id.pic10);
        listOfImageView[10] = (ImageView) findViewById(R.id.pic11);
        listOfImageView[11] = (ImageView) findViewById(R.id.pic12);

        for (int i = 0; i < listOfImageView.length; i++){
            listOfImageView[i].setTag(i);
        }
    }

    @Override
    protected void onResume(){
        super.onResume();
        for (ImageView imageView: listOfImageView){
            imageView.setOnClickListener((view)->{
                int cardNumber = (int) imageView.getTag();
                flipCard(imageView, cardNumber);
                if (numCardOver == 2){
                    Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            checkResult(firstCard, secondCard);
                        }
                    }, 1000);
                }
            });
        }
    }

    private void checkResult(int firstCard, int secondCard) {
        if (!(cardArray.get(firstCard).equals(cardArray.get(secondCard)))){
            cardArray.get(firstCard).setCovered(true);
            cardArray.get(secondCard).setCovered(true);
        }

        for (int i = 0; i < cardArray.size(); i++){
            int cardNumer = (int) listOfImageView[i].getTag();
            if (cardArray.get(i).isCovered()){
                listOfImageView[i].setImageResource(backImage);
                listOfImageView[i].setEnabled(true);
            }
        }
        numCardOver = 0;
    }


    private void flipCard(ImageView imageView, int cardNumber) {
        imageView.setImageResource(cardArray.get(cardNumber).getValue());
        cardArray.get(cardNumber).setCovered(false);
        if (numCardOver == 0){
            firstCard = cardNumber;
            numCardOver++;
            imageView.setEnabled(false);
        } else{
            secondCard = cardNumber;
            numCardOver++;
            for (ImageView image: listOfImageView){
                image.setEnabled(false);
            }
        }
    }

}
