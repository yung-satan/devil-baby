package com.example.coffeeappv3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import com.example.coffeeappv3.R;

import java.text.NumberFormat;

public class MainActivity extends AppCompatActivity {
    public static final String EXTRA_MESSAGE = "com.example.radhu.coffeeapp2.MESSAGE";

    int quantity = 2;
    String name;
    int totalPrice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void increment (View view) {
        if(quantity == 100) {
            return;
        }
        quantity++;
        displayQuantity(quantity);
    }

    public void decrement (View view) {
        if(quantity == 0) {
            return;
        }
        quantity--;
        displayQuantity(quantity);
    }

    public void submitOrder(View view) {
        EditText nameField = (EditText) findViewById(R.id.name_field);
        Editable nameEditable = nameField.getText();
        name = nameEditable.toString();

        CheckBox whippedCreamCheckBox = (CheckBox)
                findViewById(R.id.whipped_cream_checkbox);
        boolean hasWhippedCream = whippedCreamCheckBox.isChecked();

        CheckBox chocolateCheckBox = (CheckBox)
                findViewById(R.id.chocolate_checkbox);
        boolean hasChocolate = chocolateCheckBox.isChecked();

        totalPrice = calculatePrice(hasWhippedCream, hasChocolate);

        String message = createOrderSummary(name, totalPrice, hasWhippedCream, hasChocolate);

        Intent intent = new Intent(this, DisplayOrderDetails.class);
        // intent.putExtra(EXTRA_MESSAGE, message);
        //  intent.putExtra(EXTRA_MESSAGE,name);
        intent.putExtra("name", name);
        intent.putExtra("message", message);
        intent.putExtra("totalPrice", Integer.toString(totalPrice));
        startActivity(intent);
    }

    private int calculatePrice(boolean addWhippedCream, boolean addChocolate) {
        int basePrice = 5;

        if(addWhippedCream) {
            basePrice = basePrice + 1;
        }

        if(addChocolate) {
            basePrice = basePrice + 2;
        }

        return quantity * basePrice;
    }

    private String createOrderSummary(String name, int price, boolean addWhippedCream, boolean addChocolate) {
        String priceMessage = getString(R.string.order_summary_name, name);
        priceMessage += "\n" + getString(R.string.order_summary_whipped_cream, addWhippedCream);
        priceMessage += "\n" + getString(R.string.order_summary_chocolate, addChocolate);
        priceMessage += "\n" + getString(R.string.order_summary_quantity, quantity);
        priceMessage += "\n" + getString(R.string.order_summary_price,
                NumberFormat.getCurrencyInstance().format(price));
        priceMessage += "\n" + getString(R.string.thank_you);
        return priceMessage;
    }

    private void displayQuantity(int numberOfCoffees) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + numberOfCoffees);
    }
}