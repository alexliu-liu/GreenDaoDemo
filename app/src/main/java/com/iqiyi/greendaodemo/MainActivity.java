package com.iqiyi.greendaodemo;

import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.iqiyi.greendaodemo.entity.DaoSession;
import com.iqiyi.greendaodemo.entity.ListAdapter;
import com.iqiyi.greendaodemo.entity.Note;
import com.iqiyi.greendaodemo.entity.NoteDao;

import org.greenrobot.greendao.query.Query;

import java.text.DateFormat;
import java.util.Date;
import java.util.List;


public class MainActivity extends AppCompatActivity implements View.OnClickListener, ListAdapter.OnClickListener {
    public static final String TAG = "MainActivity";

    private EditText mEdit;
    private Button mButtonAdd;
    private Button mButtonUpdate;
    private Button mButtonSearch;
    private Button mButtonClear;
    private ListView mAllListView;
    private ListAdapter mAllAdapter;
    private ListView mSearchListView;
    private ListAdapter mSearchAdapter;

    private NoteDao noteDao;
    private Query<Note> noteQuery;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bindViews();

        DaoSession daoSession = ((App)getApplication()).getSession();
        noteDao = daoSession.getNoteDao();
        noteQuery = noteDao.queryBuilder().orderAsc(NoteDao.Properties.Date).build();

        showDatabase();
    }

    void bindViews() {
        mEdit = (EditText) findViewById(R.id.et_title);
        mButtonAdd = (Button) findViewById(R.id.btn_add);
        mButtonUpdate = (Button) findViewById(R.id.btn_update);
        mButtonSearch = (Button) findViewById(R.id.btn_search);
        mButtonClear = (Button) findViewById(R.id.btn_clear);
        mAllListView = (ListView) findViewById(R.id.lv_show_all);
        mAllAdapter = new ListAdapter(this, this);
        mAllListView.setAdapter(mAllAdapter);

        mSearchListView = (ListView) findViewById(R.id.lv_show_search);
        mSearchAdapter = new ListAdapter(this, null);
        mSearchListView.setAdapter(mSearchAdapter);


        mButtonAdd.setOnClickListener(this);
        mButtonUpdate.setOnClickListener(this);
        mButtonSearch.setOnClickListener(this);
        mButtonClear.setOnClickListener(this);
    }

    private void showDatabase() {
        List<Note> list = noteQuery.list();
        if (list == null)
            return;

        mAllAdapter.setData(list);

        Log.d(TAG, "show Database: ");
        for(int i = 0; i < list.size(); i++) {
            Log.d(TAG, "Id: " + list.get(i).getId() + " Text: " + list.get(i).getText() + " Data : " + list.get(i).getDate()
                + " Comment: " + list.get(i).getComment());
        }
        Log.d(TAG, "/////////////////");
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_add:
                add();
                break;
            case R.id.btn_update:
                update();
                break;
            case R.id.btn_search:
                search();
                break;
            case R.id.btn_clear:
                clear();
                break;
        }
    }

    private void add() {
        String noteText = mEdit.getText().toString();
        if (TextUtils.isEmpty(noteText))
            return;
        mEdit.setText("");

        final DateFormat df = DateFormat.getDateTimeInstance(DateFormat.MEDIUM, DateFormat.MEDIUM);
        String comment = "Added on " + df.format(new Date());

        // 插入操作，简单到只要你创建一个 Java 对象
        Note note = new Note(null, noteText, comment, new Date());
        noteDao.insert(note);
        Log.d(TAG, "Inserted new note, ID: " + note.getId());

        Toast.makeText(MainActivity.this, "添加成功", Toast.LENGTH_SHORT).show();
        showDatabase();
    }

    private Note exist(String text) {
        if (TextUtils.isEmpty(text))
            return null;

        // Query 类代表了一个可以被重复执行的查询
        Query query = noteDao.queryBuilder()
                .where(NoteDao.Properties.Text.eq(text))
                .orderAsc(NoteDao.Properties.Text)
                .build();

        List<Note> list = query.list();
        if (list != null && list.size()>0)
            return list.get(0);
        return null;
    }

    private void update() {
        String noteText = mEdit.getText().toString();
        if (TextUtils.isEmpty(noteText))
            return;
        mEdit.setText("");

        DateFormat df = DateFormat.getDateTimeInstance(DateFormat.MEDIUM, DateFormat.MEDIUM);
        Note note = exist(noteText);
        if (note != null) {
            String comment = "Update on " + df.format(new Date());
            note.setComment(comment);
            Toast.makeText(MainActivity.this, "更新成功", Toast.LENGTH_SHORT).show();
        } else {
            String comment = "Add on " + df.format(new Date());
            note = new Note(null, noteText, comment, new Date());
            noteDao.insert(note);
            Log.d(TAG, "Inserted new note, ID: " + note.getId());
            Toast.makeText(MainActivity.this, "添加成功", Toast.LENGTH_SHORT).show();
        }

        showDatabase();
    }

    private void search() {
        String searchText = mEdit.getText().toString();
        if (TextUtils.isEmpty(searchText))
            return;
        mEdit.setText("");

        // Query 类代表了一个可以被重复执行的查询
        Query query = noteDao.queryBuilder()
                .where(NoteDao.Properties.Text.like("%" + searchText + "%"))
                .orderAsc(NoteDao.Properties.Text)
                .build();

        List<Note> list = query.list();
        if (list == null) {
            Toast.makeText(MainActivity.this, "未找到匹配项", Toast.LENGTH_SHORT).show();
            return;
        }

        mSearchAdapter.setData(list);

        Log.d(TAG, "search Database: ");
        for(int i = 0; i < list.size(); i++) {
            Log.d(TAG, "Id: " + list.get(i).getId() + " Text: " + list.get(i).getText() + " Data : " + list.get(i).getDate()
                    + " Comment: " + list.get(i).getComment());
        }
        Toast.makeText(MainActivity.this, "查找成功", Toast.LENGTH_SHORT).show();
        Log.d(TAG, "/////////////////");
    }

    private void clear() {
        noteDao.deleteAll();
        Toast.makeText(MainActivity.this, "清空成功", Toast.LENGTH_SHORT).show();
        showDatabase();
    }

    @Override
    public void onItemClick(final Note note) {
        android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(this);
        builder.setTitle("确认删除？")
                .setCancelable(false)
                .setMessage("\nid: " + note.getId() + "\ntitle: " + note.getText() + "\ndate: " + note.getDate())
                .setPositiveButton("是", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        noteDao.delete(note);
                        Toast.makeText(MainActivity.this, "删除成功", Toast.LENGTH_SHORT).show();
                        showDatabase();
                    }
                })
                .setNegativeButton("否", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                }).show();

    }
}
