@Entity(tableName = "users")
data class User(
    @PrimaryKey val id: String,
    val nickname: String,
    val password: String,
    var isCreator: Boolean = false,
    var avatarPath: String? = null
)