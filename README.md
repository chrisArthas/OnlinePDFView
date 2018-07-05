# OnlinePDFView
load PDF file online

Android 在线加载PDF文件

##使用
一、
build.gradle 中加入：

allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}


	dependencies {
	        implementation 'com.github.chrisArthas:OnlinePDFView:v1.0'
	}

二、
xml 中 加入布局
    <com.chris.onlinepdf.PDFViewPager
        android:id="@+id/pdf_viewpager"
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        />
三、
activity 或 fragment中 初始化 OnlinePDFViewPager

        onlinePDFViewPager = new OnlinePDFViewPager(this, url, this,false);
        onlinePDFViewPager.setId(R.id.pdf_viewpager);

在onSuccess中设置adapter
         adapter = new PDFPagerAdapter(this, FileUtil.extractFileNameFromURL(url));
         onlinePDFViewPager.setAdapter(adapter);
         setContentView(onlinePDFViewPager);