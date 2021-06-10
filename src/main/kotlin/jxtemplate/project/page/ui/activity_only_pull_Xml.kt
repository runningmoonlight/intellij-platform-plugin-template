package jxtemplate.project.page.ui

/**
 * Created by liuheng on 2021/6/10.
 */
fun activity_only_pull_Xml(
        applicationPackage: String?,
        page: String
) = """
<?xml version="1.0" encoding="utf-8"?>
<com.jd.pingou.base.jxwidget.JxTestSpeedFrameLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    app:reportPageId="@string/${page}_reportPageId"
    android:focusableInTouchMode="true"
    android:focusable="true"
    xmlns:app="http://schemas.android.com/apk/res-auto">

    <LinearLayout
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        android:orientation="vertical" >

        <androidx.constraintlayout.widget.ConstraintLayout
            android:id="@+id/cl_title_bar"
            app:layout_constraintTop_toTopOf="parent"
            android:layout_width="match_parent"
            android:layout_height="44dp">
            <ImageView
                android:id="@+id/iv_back"
                android:layout_width="30dp"
                android:layout_height="30dp"
                android:visibility="gone"
                app:layout_constraintTop_toTopOf="parent"
                app:layout_constraintBottom_toBottomOf="parent"
                app:layout_constraintLeft_toLeftOf="parent"
                android:layout_marginStart="10dp"
                android:scaleType="centerInside"
                />
            <TextView
                android:id="@+id/tv_title"
                app:layout_constraintLeft_toLeftOf="parent"
                app:layout_constraintRight_toRightOf="parent"
                app:layout_constraintTop_toTopOf="parent"
                app:layout_constraintBottom_toBottomOf="parent"
                android:layout_width="wrap_content"
                android:textSize="18dp"
                android:textColor="@color/black"
                android:layout_height="wrap_content"
                android:includeFontPadding="false"
                />

            <com.jd.pingou.widget.message.PgMessageView
                android:id="@+id/message_view"
                android:layout_width="wrap_content"
                android:layout_height="wrap_content"
                app:layout_constraintTop_toTopOf="parent"
                app:layout_constraintBottom_toBottomOf="parent"
                app:layout_constraintEnd_toEndOf="parent"
                app:type="OnlyMessage"
                app:anchorMsgIcon="true"
                android:layout_marginEnd="4dp"/>
        </androidx.constraintlayout.widget.ConstraintLayout>

        <${applicationPackage}.common.PullToRefreshView
            android:id="@+id/view_pull_to_refresh"
            android:layout_width="match_parent"
            android:layout_height="match_parent"
            android:cacheColorHint="@null"
            android:fadingEdge="none"
            android:listSelector="@null"/>
    </LinearLayout>

    <com.jd.pingou.widget.loading.PgCommonNetLoadingStyle2
        android:id="@+id/view_center_loading"
        android:layout_width="60dp"
        android:layout_height="60dp"
        android:layout_gravity="center"
        android:visibility="gone"
        app:layout_constraintLeft_toLeftOf="parent"
        app:layout_constraintTop_toTopOf="parent"
        app:layout_constraintBottom_toBottomOf="parent"
        app:layout_constraintRight_toRightOf="parent"/>


</com.jd.pingou.base.jxwidget.JxTestSpeedFrameLayout>
""".trimIndent()