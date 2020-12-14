package com.github.hbtruong2017.oft;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.Toast;

import com.github.hbtruong2017.oft.ui.appointment.BarberActivity;
import com.github.hbtruong2017.oft.ui.appointment.ConfirmationActivity;
import com.github.hbtruong2017.oft.ui.appointment.ServiceActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

public class StoreActivity extends AppCompatActivity {

    private ArrayList<Product> list = new ArrayList<Product>();
    private CustomizedAdapter customizedAdapter;
    private int counter=0;

    private Product sC;
    private Product sM;
    private Product cC;
    private Product cM;
    private Product gC;
    private Product gM;
    private Product wC;
    private Product wM;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store);
        Toast.makeText(StoreActivity.this, "Click on the image to get to the product's webpage.", Toast.LENGTH_LONG ).show();
        ListView products = findViewById(R.id.products);
        Toolbar mToolbar = (Toolbar) findViewById(R.id.app_bar);
        mToolbar.setTitle("Store");
        setSupportActionBar(mToolbar);

        sC = new Product("LIVING PROOF Perfect hair Day™ Dry Shampoo 198ml", "SG$27.00","https://www.selfridges.com/SG/en/cat/living-proof-perfect-hair-day%E2%84%A2-dry-shampoo-198ml_334-3006219-LP101708/?cm_mmc=PLA-_-GoogleSG-_-BEAUTY-_-LIVINGPROOF&POR=Y&utm_source=google&utm_medium=cpc&utm_campaign=na_na_pla-b_na_na_na_INT_SG-PLA-BEAUTY-BATHANDBODYCARE-NEW&gclid=CjwKCAiAnfjyBRBxEiwA-EECLPwX2aJTQDhtXCafiZdprG8IMSPPCx3LrwGDT3NaKUtjlMMa5Je6ERoCvKAQAvD_BwE&gclsrc=aw.ds", "https://images.selfridges.com/is/image/selfridges/334-3006219-LP101708_ALT10?$PDP_M_ZOOM$&defaultImage=334-3006219-LP101708_M");
        sM = new Product("Aromachologie Body & Strength Shampoo","SG$35.00","https://sg.loccitane.com/body-strength-shampoo,27,1,1952,752088.htm?gclid=CjwKCAiAnfjyBRBxEiwA-EECLIkMXRWH2fTGk_1CrCrbfex74YrzzCbXUbE4oCpZ6p4AktPd7Z_b-BoCTYUQAvD_BwE","https://img.loccitane.com/P.aspx?l=en-SG&s=500&e=png&name=body-strength-shampoo-loccitane&id=17SH300D15&v=2");
        cC=  new Product("Palmer's, Coconut Oil Formula, Repairing Conditioner, 8.5 fl oz (250 ml)", "SG$7.85", "https://sg.iherb.com/pr/Palmer-s-Coconut-Oil-Formula-Repairing-Conditioner-8-5-fl-oz-250-ml/51537?gclid=CjwKCAiAnfjyBRBxEiwA-EECLHEhSfpXdaZYK2fNlcn51tPNsmQjwIndF4d88blXYPDmrMi6kty9mBoCjqIQAvD_BwE&gclsrc=aw.ds", "https://s3.images-iherb.com/pal/pal03308/l/7.jpg");
        cM=  new Product("Philip B, Russian Amber Conditioner, 6 fl oz (178 ml)", "SG$239.23","https://sg.loveletter.iherb.com/pr/Philip-B-Russian-Amber-Conditioner-6-fl-oz-178-ml/96446?gclid=CjwKCAiAnfjyBRBxEiwA-EECLA4QgoUAT4O7bRh4piPE7mX_POPBAq2qwq047gUjADex-pvbnnlCGBoCCtwQAvD_BwE&gclsrc=aw.ds", "https://s3.images-iherb.com/pib/pib00094/l/0.jpg" );
        gC=  new Product("Aubrey Organics, Men's Stock, Hair Gel, Ginseng Biotin, 6 fl oz (177 ml)", "SG$10.33", "https://sg.iherb.com/pr/Aubrey-Organics-Men-s-Stock-Hair-Gel-Ginseng-Biotin-6-fl-oz-177-ml/7950?gclid=CjwKCAiAnfjyBRBxEiwA-EECLDQBa9hQ5Iuvsgq07OcQHM7r9j2RGbNDA2g-497Z6SPNt5F0zyd_LhoCGQkQAvD_BwE&gclsrc=aw.ds", "https://s3.images-iherb.com/aub/aub04004/l/11.jpg");
        gM=  new Product("Giovanni, L.A. Natural, Styling Gel, Strong Hold, 6.8 fl oz (200 ml)", "SG$10.08", "https://sg.iherb.com/pr/Giovanni/6418", "https://s3.images-iherb.com/gio/gio10008/l/3.jpg");
        wC=  new Product("Giovanni, Wicked Texture, The Definition of Pomade, 2 oz (57 g)", "SG$11.24","https://sg.iherb.com/pr/Giovanni/10949?gclid=CjwKCAiAnfjyBRBxEiwA-EECLNyJk5dhkKDtMaylhfglXaPXljlUAKuC6zfx-oiKfuf1lMq73EyOLhoCmHAQAvD_BwE&gclsrc=aw.ds", "https://s3.images-iherb.com/gio/gio18169/l/3.jpg");
        wM=  new Product("SACHAJUAN Hair Wax 75ml", "SGD$32.50", "https://www.selfridges.com/SG/en/cat/sachajuan-hair-wax-75ml_373-3006253-32132007/?previewSize=75ml&cm_mmc=PLA-_-GoogleSG-_-BEAUTY-_-SACHAJUAN&POR=Y&utm_source=google&utm_medium=cpc&utm_campaign=na_na_pla-b_na_na_na_INT_SG-PLA-BEAUTY-MENSGROOMING-NEW&gclid=CjwKCAiAnfjyBRBxEiwA-EECLGd4V_028qA15fEChQXlbTSN2pUFNYRVjt9UYlVngjylMfcBZySbExoCqGMQAvD_BwE&gclsrc=aw.ds", "https://images.selfridges.com/is/image/selfridges/373-3006253-32132007_ALT10?$PDP_M_ZOOM$&defaultImage=373-3006253-32132007_M");
        addAllBack(products);
        customizedAdapter = new CustomizedAdapter(this, list);
        products.setAdapter(customizedAdapter);
        products.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(list.get(position).getLink())));
            }
        });

        // Bottom bar
        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.next:
                        Intent intent = new Intent(StoreActivity.this, MainActivity.class);
                        startActivity(intent);
                        break;
                }
                return true;
            }
        });
    }

    public void onCheckboxClicked(View view) {
        boolean checked = ((CheckBox) view).isChecked();
        ListView products = findViewById(R.id.products);
        switch (view.getId()){
            case R.id.checkbox_menGel:
                if (checked){
                    checkCheckbox(products, gC, gM);
                } else {
                    uncheckCheckbox(products, gC, gM);
                }
                break;
            case R.id.checkbox_menWax:
                if (checked){
                    checkCheckbox(products, wC, wM);
                } else {
                    uncheckCheckbox(products, wC, wM);
                }
                break;
            case R.id.checkbox_MenCon:
                if (checked){
                    checkCheckbox(products, cC, cM);
                } else {
                    uncheckCheckbox(products, cC, cM);
                }
                break;
            case R.id.checkbox_MenShamp:
                if (checked){
                    checkCheckbox(products, sC, sM);
                } else {
                    uncheckCheckbox(products, sC, sM);
                }
                break;
        }
    }

    private void addAllBack(ListView products){
        //Shampoo cheap
        list.add(sC);
        //Shampoo medium
        list.add(sM);
        //Conditioner cheap
        list.add(cC);
        //Conditioner expensive
        list.add(cM);
        //Repair Oil cheap
        //list.add(rC);
        //Repair Oil medium
        //list.add(rM);
        //Wax cheap
        list.add(wC);
        //Wax medium
        list.add(wM);
        //Gel cheap
        list.add(gC);
        //Gel cheap
        list.add(gM);
        customizedAdapter = new CustomizedAdapter(this, list);
        products.setAdapter(customizedAdapter);

    }

    private void checkCheckbox(ListView product, Product cheap, Product expensive){
        if(counter==0){
            list.clear();
        }
        counter++;
        list.add(cheap);
        list.add(expensive);
        customizedAdapter = new CustomizedAdapter(this, list);
        product.setAdapter(customizedAdapter);
    }


    private void uncheckCheckbox(ListView product, Product cheap, Product expensive){
        counter--;
        if(counter==0){
            addAllBack(product);
        }else {
            list.remove(cheap);
            list.remove(expensive);
            customizedAdapter = new CustomizedAdapter(this, list);
            product.setAdapter(customizedAdapter);
        }
    }
}
