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

import static com.m7.imkfsdk.utils.permission.request.RequestBackgroundLocationPermission.ACCESS_BACKGROUND_LOCATION;

import android.os.Build;
import androidx.fragment.app.FragmentActivity;

import com.m7.imkfsdk.utils.permission.request.PermissionBuilder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


/**
 * An internal class to provide specific scope for passing permissions param.
 *
 * @Author: chenbo
 * @Date: 2020/6/17
 */
public class PermissionCollection {

    private static final String TAG = "PermissionCollection";

    private final FragmentActivity activity;

    public PermissionCollection(FragmentActivity activity) {
        this.activity = activity;
    }

    /**
     * All permissions that you want to request.
     *
     * @param permissions A vararg param to pass permissions.
     */
    public PermissionBuilder permissions(String... permissions) {
        return permissions(new ArrayList<>(Arrays.asList(permissions)));
    }

    /**
     * All permissions that you want to request.
     *
     * @param permissions A vararg param to pass permissions.
     */
    public PermissionBuilder permissions(List<String> permissions) {
        Set<String> permissionSet = new HashSet<>(permissions);
        boolean requireBackgroundLocationPermission = false;
        Set<String> permissionsWontRequest = new HashSet<>();
        if (permissionSet.contains(ACCESS_BACKGROUND_LOCATION)) {
            int osVersion = Build.VERSION.SDK_INT;
            int targetSdkVersion = activity.getApplicationInfo().targetSdkVersion;
            if (osVersion >= 30 && targetSdkVersion >= 30) {
                requireBackgroundLocationPermission = true;
                permissionSet.remove(ACCESS_BACKGROUND_LOCATION);
            } else if (osVersion < 29) {
                // If app runs under Android Q, there's no ACCESS_BACKGROUND_LOCATION permissions.
                // We remove it from request list, but will append it to the request callback as denied permission.
                permissionSet.remove(ACCESS_BACKGROUND_LOCATION);
                permissionsWontRequest.add(ACCESS_BACKGROUND_LOCATION);
            }
        }
        return new PermissionBuilder(activity, permissionSet, requireBackgroundLocationPermission, permissionsWontRequest);
    }

}
