(ns ordinance.facts
  "Municipal-ordinance compliance catalog for Seoul -- the NINTH
  municipality-level entry (see cloud-itonami-municipality-jpn-tokyo,
  -usa-washington-dc, -gbr-london, -can-toronto, -deu-berlin, -fra-paris,
  -nld-amsterdam, -esp-madrid for the first eight) per ADR-2607141700
  (cloud-itonami-compliance-fact-federation).

  Every entry cites an OFFICIAL legal.seoul.go.kr (Seoul Metropolitan
  Government's own official English-translation legal portal) URL --
  never fabricated. An ordinance not in this table has NO spec-basis,
  full stop; extend `catalog`, do not invent an id/url/number.

  Both entries below were directly WebFetch-verified against the live
  legal.seoul.go.kr page on 2026-07-15. The Personal Information
  Protection Ordinance entry cites the most recent promulgation shown
  on its comparison page (Enactment No. 9487, 2025-01-03) as a revision
  date -- the ordinance's original enactment date was not independently
  confirmed, so `:enacted-date` is deliberately omitted for that entry
  rather than guessed.")

(def catalog
  "municipality-slug -> vector of ordinance entries."
  {"seoul"
   [{:ordinance/id "seoul.administrative-information-disclosure-ordinance"
     :ordinance/title "Seoul Metropolitan Government Ordinance on Disclosure of Administrative Information for Open City Administration"
     :ordinance/municipality "seoul"
     :ordinance/country "KOR"
     :ordinance/kind :ordinance
     :ordinance/number "Promulgation No. 3792"
     :ordinance/url "https://legal.seoul.go.kr/legal/english/%20front/page/law.html?pAct=lawView&pPromNo=2130"
     :ordinance/url-provenance :official-seoul-legal-service
     :ordinance/enacted-date "2000-10-25"
     :ordinance/retrieved-at "2026-07-15"
     :ordinance/topic #{:information-disclosure :transparency}}
    {:ordinance/id "seoul.personal-information-protection-ordinance"
     :ordinance/title "Seoul Metropolitan Government Ordinance on Protection of Personal Information"
     :ordinance/municipality "seoul"
     :ordinance/country "KOR"
     :ordinance/kind :ordinance
     :ordinance/number "Enactment No. 9487"
     :ordinance/url "https://legal.seoul.go.kr/legal/english/front/page/law.html?pAct=lawComparison&pPromNo=3710"
     :ordinance/url-provenance :official-seoul-legal-service
     :ordinance/last-revised-date "2025-01-03"
     :ordinance/retrieved-at "2026-07-15"
     :ordinance/topic #{:data-protection :privacy}}]})

(defn spec-basis [muni] (get catalog muni))

(defn coverage
  ([] (coverage (keys catalog)))
  ([munis]
   (let [have (filter catalog munis)
         missing (remove catalog munis)]
     {:requested (count munis)
      :covered (count have)
      :covered-municipalities (vec (sort have))
      :missing-municipalities (vec (sort missing))
      :note (str "cloud-itonami-municipality-kor-seoul Wave 0 (ADR-2607141700): "
                 (count (get catalog "seoul")) " Seoul entries seeded with "
                 "an official legal.seoul.go.kr citation. Extend "
                 "`ordinance.facts/catalog`, never fabricate an id/url.")})))

(defn by-topic [muni topic]
  (filterv #(contains? (:ordinance/topic %) topic) (spec-basis muni)))
