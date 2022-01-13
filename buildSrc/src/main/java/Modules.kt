object Modules {

    const val shared = ":shared"
    const val baseTests = ":androidApp:base-tests"
    const val navigation = ":androidApp:navigation"
    const val theme = ":androidApp:theme"
    const val domain = ":androidApp:domain"
    const val data = ":androidApp:data"
    const val common = ":androidApp:common"
    const val featureHome = ":androidApp:features:home"
    const val featureEstablishment = ":androidApp:features:establishment"
    const val featureHistoric = ":androidApp:features:historic"
    const val featureProfile = ":androidApp:features:profile"
    const val featureLogin = ":androidApp:features:login"
    const val featureCreateAccount = ":androidApp:features:createaccount"
    const val featureForgotPassword = ":androidApp:features:forgotpassword"
    const val featurePaymentMethod = ":androidApp:features:payment"
    const val featureChangePassword = ":androidApp:features:changepassword"

    val modules = listOf(
        shared,
        domain,
        data,
        common,
    )

    val features = listOf(
        featureHome,
        featureEstablishment,
        featureHistoric,
        featureProfile,
        featureLogin,
        featureCreateAccount,
        featureForgotPassword,
        featurePaymentMethod,
        featureChangePassword,
    )

    val tests = listOf(
        common,
    )
}