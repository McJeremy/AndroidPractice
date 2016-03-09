在android中只有在主线程才能对ui进行更新操作，而其它线程不能直接对ui进行操作  
android本身是一个多线程的操作系统，我们不能把所有的操作都放在主线程中操作 ，比如一些耗时操作。  
如果放在主线程中 会造成阻塞 而当阻塞事件过长时 系统会抛出anr异常。所以我们要使用异步任务。  
android为我们提供了一个封装好的组件asynctask。  
AsyncTask可以在子线程中更新ui，封装简化了异步操作。适用于简单的异步处理。如果多个后台任务时就要使用Handler了 在这里就不再说明。  
AsyncTask通常用于被继承。AsyncTask定义了三种泛型类型<Params,Progress,Result>  
>Params:启动任务时输入的参数类型,比如一个网络图片的地址    Progress:后台任务执行的百分比,执行进度（在doInBackground中通过publishProgress发布）    Result:执行任务完成后返回结果的类型(比如下载图片，那么该结果是Bitmap)    

***
  
继承AsyncTask后要重写的方法有：  

- doInBackgroud:必须重写，异步执行后台线程要完成的任务，耗时任务要写在这里，并且在这里不能操作ui。可以调用 publishProgress方法来更新实时的任务进度
它需要参数Params，并且返回Result类型。
如： 
```java
	@Override
	protected Bitmap doInBackground(String... url) {return null;}
```
- onPreExecute:执行耗时操作前调用，可以完成一些初始化操作
如：
```java
	@Override
        public void onPreExecute()
        {
            //开始下载前
            super.onPreExecute();
            if(image!=null)
            {
                image.setVisibility(View.GONE);
            }
            progress.show();
            progress.setProgress(0);
        }
```
- onPostExecute:在doInBackground 执行完成后，主线程调用此方法，可以在此方法中操作ui
它接收doInBackground的执行结果，参数是它的返回参数Result，
如：
```java
	@Override
        protected  void onPostExecute(Bitmap img)
        {
            //下载完成后
            super.onPostExecute(img);

            progress.hide();
            image.setImageBitmap(img);
            image.setVisibility(View.VISIBLE);
        }
```
- onProgressUpdate:在doInBackgroud方法中调用publishProgress方法，更新任务的执行进度后 就会调用这个方法
如：
```java
	@Override
        public void onProgressUpdate(Integer... values)
        {
            super.onProgressUpdate(values);
            progress.setProgress(values[0]);
        }
```     
