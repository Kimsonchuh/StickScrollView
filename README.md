StickyScrollView
========

StickyScrollView is an opensource Android library that alows developers to easily implements the effect of Sticky ScrollView, just like the effect of Taobao.


Example
-------

![Framed example screenshot](https://github.com/Kimsonchuh/StickScrollView/blob/master/images/stickyScrollview.gif)


Usage
-------

```xml
<?xml version="1.0" encoding="utf-8"?>
<com.kimson.library.StickyScrollView 
	xmlns:android="http://schemas.android.com/apk/res/android"
    android:id="@+id/sticky_scroll"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:scrollbars="none">

    <LinearLayout
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        android:orientation="vertical">

        <!-- child_0 -->
        <LinearLayout
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            android:gravity="center"
            android:orientation="vertical">

            <ImageView
                android:id="@+id/item_1"
                android:clickable="true"
                android:layout_width="match_parent"
                android:layout_height="200dp"
                android:layout_marginTop="2dp"
                android:scaleType="centerCrop"
                android:src="@mipmap/minicon_001" />
			
				...
        </LinearLayout>


        <!-- child_1 -->
        <ScrollView
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            android:scrollbars="vertical">

            <LinearLayout
                android:layout_width="match_parent"
                android:layout_height="match_parent"
                android:gravity="center"
                android:orientation="vertical">

                <!-- divider -->
                <TextView
                    android:layout_width="match_parent"
                    android:layout_height="30dp"
                    android:gravity="center"
                    android:textColor="@android:color/white"
                    android:textSize="16sp"
                    android:text="Divider"
                    android:background="#00AA50"/>

                <ImageView
                    android:id="@+id/item_6"
                    android:clickable="true"
                    android:layout_width="match_parent"
                    android:layout_height="200dp"
                    android:layout_marginTop="2dp"
                    android:scaleType="centerCrop"
                    android:src="@mipmap/minicon_006" />
	
					...

            </LinearLayout>
        </ScrollView>
    </LinearLayout>
</com.kimson.library.StickyScrollView>

```



License
-------
	
	Licensed under the Apache License, Version 2.0 (the "License");
	you may not use this file except in compliance with the License.
	You may obtain a copy of the License at
	
	    http://www.apache.org/licenses/LICENSE-2.0
	
	Unless required by applicable law or agreed to in writing, software
	distributed under the License is distributed on an "AS IS" BASIS,
	WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
	See the License for the specific language governing permissions and
	limitations under the License.
