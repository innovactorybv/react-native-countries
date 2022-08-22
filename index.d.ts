declare module ReactNativeCountries {
    const getCountryNamesWithCodes: () => Promise<{ code: string; name: string }[]>
    const getCountryCodes: () => Promise<string[]>
    const getCountryNames: () => Promise<string[]>
}