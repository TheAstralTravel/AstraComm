class SessionManager(context: Context) {
    private val sharedPref = context.getSharedPreferences("AstraComm", Context.MODE_PRIVATE)

    var currentUserId: String?
        get() = sharedPref.getString("CURRENT_USER_ID", null)
        set(value) = sharedPref.edit().putString("CURRENT_USER_ID", value).apply()
}