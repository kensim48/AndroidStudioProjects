 //Button
        buttonSign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(LoginMain.this, RegistrationMain.class);
                startActivity(i);
            }
        });
//Toast
Toast.makeText(LoginMain.this, errorMsg, Toast.LENGTH_LONG).show();

//SharedPreferences
@Override
protected void onCreate (Bundle savedInstanceState) {
//other code not shown
mPreferences = getSharedPreferences(sharedPrefFile,
MODE_PRIVATE);
String Rate_text = mPreferences.getString(KEY,defaultValue);
}
@Override
protected void onPause () {
super .onPause();
SharedPreferences.Editor preferencesEditor =
mPreferences.edit();
preferencesEditor.putString(KEY, value);
preferencesEditor.apply();
}


//Menu
 @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        if(id == R.id.menu_open_map_app){
            String location = "Changi Airport";
            Uri.Builder builder = new Uri.Builder();
            builder.scheme("geo")
                    .opaquePart("0.0")
                    .appendQueryParameter("q",location);
            Uri geoLocation = builder.build();
            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setData(geoLocation);

            if (intent.resolveActivity(getPackageManager()) != null){
                startActivity(intent);
            }
            return true;
        }

        return super.onOptionsItemSelected(item);
    }