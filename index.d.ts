declare module "react-native-countries" {
    export const getCountryNamesWithCodes: () => Promise<{ code: string; name: string }[]>
    export const getCountryCodes: () => Promise<string[]>
    export const getCountryNames: () => Promise<string[]>
}