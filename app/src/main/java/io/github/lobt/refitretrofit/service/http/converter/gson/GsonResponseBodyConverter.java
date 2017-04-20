/*
 * Copyright (C) 2015 Square, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package io.github.lobt.refitretrofit.service.http.converter.gson;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.internal.LinkedTreeMap;
import com.google.gson.stream.JsonReader;

import java.io.IOException;

import io.github.lobt.refitretrofit.service.http.model.HttpModel;
import okhttp3.ResponseBody;
import retrofit2.Converter;

final class GsonResponseBodyConverter<T> implements Converter<ResponseBody, T> {
    private final Gson gson;
    private final TypeAdapter<T> adapter;
    private final String queryKey;

    GsonResponseBodyConverter(Gson gson, TypeAdapter<T> adapter) {
        this.gson = gson;
        this.adapter = adapter;
        this.queryKey = null;
    }

    GsonResponseBodyConverter(Gson gson, TypeAdapter<T> adapter, String queryKey) {
        this.gson = gson;
        this.adapter = adapter;
        this.queryKey = queryKey;
    }

    @Override
    public T convert(ResponseBody value) throws IOException {

        if (queryKey != null) {
            HttpModel<LinkedTreeMap> httpModel = gson.fromJson(value.string(), HttpModel.class);
            LinkedTreeMap map = httpModel.data;

            HttpModel result = new HttpModel();
            result.code = httpModel.code;
            result.message = httpModel.message;
            result.data = map.get(queryKey);

            value = ResponseBody.create(value.contentType(), gson.toJson(result));
        }

        JsonReader jsonReader = gson.newJsonReader(value.charStream());
        try {
            return adapter.read(jsonReader);
        } finally {
            value.close();
        }
    }
}
