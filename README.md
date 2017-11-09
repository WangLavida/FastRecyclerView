# FastRecyclerView

[![](https://jitpack.io/v/WangLavida/FastRecyclerView.svg)](https://jitpack.io/#WangLavida/FastRecyclerView)

自定义RecyclerView上下加载下拉刷新
##使用步骤
1. 根目录build.gradle

		allprojects {
		    repositories {
		        jcenter()
		        maven { url 'https://jitpack.io' }
		    }
		}
2. app目录build.gradle

		 compile 'com.github.WangLavida:FastRecyclerView:1.0.1'


3.  添加布局
  
		<com.wolf.fastlibrary.FastSwipeRefreshLayout
        android:id="@+id/fast_layout"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"></com.wolf.fastlibrary.FastSwipeRefreshLayout>
4. 代码上拉下拉

	  	fastSwipeRefreshLayout.init(new LinearLayoutManager(this), wxAdapter, new FastLoadMore() {
	            @Override
	            public void refresh() {
	               //下拉刷新
	            }
	
	            @Override
	            public void loadMore() {
	               //上拉加载
	            }
	        });