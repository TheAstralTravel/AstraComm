object AuthManager {
    private const val PREFIX = "AC/"
    private val registeredNicknames = mutableSetOf<String>()

    suspend fun register(
        context: Context,
        nickname: String,
        password: String
    ): Result<User> = withContext(Dispatchers.IO) {
        try {
            require(nickname.isNotBlank()) { "Никнейм не может быть пустым" }
            require(!registeredNicknames.contains(nickname)) { "Никнейм уже занят" }

            val db = AppDatabase.getInstance(context)
            val lastUser = db.userDao().getLastUser()
            val newId = generateNextId(lastUser?.assignedId)

            val user = User(
                assignedId = newId,
                nickname = "$PREFIX$nickname",
                password = password.hashCode().toString(),
                isCreator = newId == "0001"
            )

            db.userDao().insert(user)
            registeredNicknames.add(nickname)

            if (newId == "0001") {
                user.creatorBadgePath = "badge_owner.png"
                NotificationManager.notifyCreator(context, user)
            }

            Result.success(user)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    private fun generateNextId(lastId: String?): String {
        return lastId?.let { 
            "%04d".format(it.toInt() + 1) 
        } ?: "0001"
    }
}