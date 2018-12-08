Changes

1. CharaViewHolder inner class in CharaAdapter is declared as static

2. In DataEntryActivity, for TODO 8.8, add a finish() command after setResult() to automatically go back:

                    Intent returnIntent = new Intent();
                    setResult(Activity.RESULT_OK, returnIntent);
                    finish(); //TODO <---- put this line in

2. If you have more than one database row with the same name field,
the app will crash when you swipe to delete.
when the app restarts, see the recyclerview and
you will see that all rows with the name field have been deleted.
This would require some re-designing so I won't be able to fix this bug this year!
