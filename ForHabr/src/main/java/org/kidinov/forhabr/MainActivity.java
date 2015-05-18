package org.kidinov.forhabr;

import android.app.ListActivity;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainActivity extends ListActivity {
    public static final String TAG = "FOR_HABR";
    private Random randomGenerator = new Random();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, "onCreate");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //генерируем размер нашего листа
        int size = getRandomNumber(200);

        ListView listView = getListView();
        //Создаем инстанс нашего, кастомного адаптера
        ArrayAdapter<Integer> customAdapter = new CustomAdapter(this, R.layout.list_view_row, generateListOfColors(size).toArray(new Integer[0]), listView);
        listView.setAdapter(customAdapter);

        //Указываем ListView то мы хотим режим с мультивыделеним
        listView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE_MODAL);
        //Указываем обработчик такого режима
        listView.setMultiChoiceModeListener(new MultiChoiceImpl(listView));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        Log.d(TAG, "onCreateOptionsMenu");
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    private List<Integer> generateListOfColors(int size) {
        List<Integer> result = new ArrayList<Integer>();
        for (int i = 0; i < size; i++) {
            result.add(generateRandomColor());
        }
        return result;
    }

    //Генерируем случайный цвет
    private int generateRandomColor() {
        return Color.rgb(getRandomNumber(256), getRandomNumber(256), getRandomNumber(256));
    }

    private int getRandomNumber(int maxValue) {
        return randomGenerator.nextInt(maxValue);
    }

}
