package com.mandeum.dessert39.Login.ServerApi.Model

import com.mandeum.dessert39.Main.Alarm.AlarmData

data class PushModel(val Connection:String, val errCode: String, val list: ArrayList<AlarmData> )
