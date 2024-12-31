/*
 * Copyright (C)  guolin, PermissionX Open Source Project
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

package com.m7.imkfsdk.utils.permission;

import android.content.Context;
import android.content.pm.PackageManager;
import androidx.fragment.app.FragmentActivity;
import androidx.core.content.ContextCompat;

/**
 * @Description
 * @Author: chenbo
 * @Date: 2020/6/17
 */
public class PermissionX {

    /**
     * Init PermissionX to make everything prepare to work.
     *
     * @param activity An instance of FragmentActivity
     */
    public static PermissionCollection init(FragmentActivity activity) {
        return new PermissionCollection(activity);
    }

    /**
     * A helper function to check a permission is granted or not.
     *
     * @param context    Any context, will not be retained.
     * @param permission Specific permission name to check. e.g. [android.Manifest.permission.CAMERA].
     * @return True if this permission is granted, False otherwise.
     */
    public static boolean isGranted(Context context, String permission) {
        return ContextCompat.checkSelfPermission(context, permission) == PackageManager.PERMISSION_GRANTED;
    }

}
