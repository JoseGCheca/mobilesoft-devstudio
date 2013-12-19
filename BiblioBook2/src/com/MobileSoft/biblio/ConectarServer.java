package com.MobileSoft.biblio;

import java.io.DataOutputStream;
import java.io.OutputStream;
import java.net.Socket;

import android.os.AsyncTask;

class ConectarServer extends AsyncTask<Void, Void, Void>{
    // Do the long-running work in here
    /*protected Long doInBackground( opcion) {
        /*int count = urls.length;
        long totalSize = 0;
        for (int i = 0; i < count; i++) {
            totalSize += Downloader.downloadFile(urls[i]);
            publishProgress((int) ((i / (float) count) * 100));
            // Escape early if cancel() is called
            if (isCancelled()) break;
        }
        return totalSize;*/
    

    // This is called each time you call publishProgress()
    protected void onProgressUpdate(Integer... progress) {
      //  setProgressPercent(progress[0]);
    }

    // This is called when doInBackground() is finished
    protected void onPostExecute(Long result) {
        //showNotification("Downloaded " + result + " bytes");
    }

	@Override
	protected Void doInBackground(Void... params) {
		try{
			
			Socket skCliente = new Socket( "213.143.51.238", 16000);
			
			OutputStream aux= skCliente.getOutputStream();
			DataOutputStream flujo = new DataOutputStream( aux);
			flujo.writeUTF("Hola mundo!");
			
			skCliente.close();
		}
		catch(Exception e){
			System.out.println(e.getMessage());
		}
		
		return null;
	}
}