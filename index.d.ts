
export declare type CountryNameWithCode = { code: string; name: string }
interface RNCountries {
    getCountryNamesWithCodes: () => Promise<CountryNameWithCode[]>
    getCountryCodes: () => Promise<string[]>
    getCountryNames: () => Promise<string[]>
}
declare const RNCountries: RNCountries

export const getCountryNamesWithCodes: typeof RNCountries.getCountryNamesWithCodes
export const getCountryCodes: typeof RNCountries.getCountryCodes
export const getCountryNames: typeof RNCountries.getCountryNames

export default RNCountries