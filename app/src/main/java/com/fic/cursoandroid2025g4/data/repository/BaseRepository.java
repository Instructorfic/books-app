package com.fic.cursoandroid2025g4.data.repository;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public abstract class BaseRepository {

    protected <T> void executeAsync(Call<T> call, RepositoryCallback<T> callback){
        call.enqueue(new Callback<T>() {
            @Override
            public void onResponse(Call<T> call, Response<T> response) {
                if(response.isSuccessful() && response.body() != null){
                    callback.onSuccess(response.body());
                }else{
                    callback.onError(new Exception("Ocurrió un error en la respuesta del servicio: " + response.code()));
                }
            }

            @Override
            public void onFailure(Call<T> call, Throwable t) {
                callback.onError(t);
            }
        });
    }


    public interface RepositoryCallback<T> {
        void onSuccess(T result);
        void onError(Throwable error);
    }
}
