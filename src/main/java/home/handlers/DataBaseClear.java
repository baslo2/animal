package home.handlers;

import home.db.OperationsDB;

public final class DataBaseClear implements ICleaner {

    @Override
    public void clean() {
        OperationsDB.deleteAll();
    }
}
