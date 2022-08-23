
interface RNCountries {
    getCountryNamesWithCodes: () => Promise<{ code: string; name: string }[]>
    getCountryCodes: () => Promise<string[]>
    getCountryNames: () => Promise<string[]>
}
declare const RNCountries: RNCountries

export const getCountryNamesWithCodes: typeof RNCountries.getCountryNamesWithCodes
export const getCountryCodes: typeof RNCountries.getCountryCodes
export const getCountryNames: typeof RNCountries.getCountryNames

export default RNCountries