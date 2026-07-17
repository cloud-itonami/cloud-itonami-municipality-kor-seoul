(ns culture.facts
  "Regional-culture catalog for Seoul -- local dishes, protected products,
  beverages, festivals and heritage sites, piggybacked onto this
  municipality compliance repo per ADR-2607171400
  (cloud-itonami-municipality-culture-catalog, in com-junkawasaki/root),
  sibling namespace to `ordinance.facts` (ADR-2607141700).

  Every entry cites a source URL that was actually fetched and read on
  :culture/retrieved-at -- never fabricated. Summaries state only what the
  cited source confirms. An item not in this table has NO spec-basis, full
  stop; extend `catalog`, do not invent an id/url.")

(def catalog
  "municipality-slug -> vector of culture entries."
  {"seoul"
   [{:culture/id "seoul.dish.seolleongtang"
     :culture/name "Seolleongtang"
     :culture/name-local "설렁탕"
     :culture/municipality "seoul"
     :culture/country "KOR"
     :culture/kind :dish
     :culture/summary "Korean soup made from ox bones and brisket, originating from the Seonnongdan altar rituals of the Joseon period in Seoul."
     :culture/url "https://en.wikipedia.org/wiki/Seolleongtang"
     :culture/url-provenance :wikipedia-en
     :culture/retrieved-at "2026-07-17"}
    {:culture/id "seoul.dish.tteok-bokki"
     :culture/name "Tteokbokki"
     :culture/name-local "떡볶이"
     :culture/municipality "seoul"
     :culture/country "KOR"
     :culture/kind :dish
     :culture/summary "Simmered rice-cake dish whose modern gochujang-seasoned version was first sold in the 1950s in Seoul's Sindang district, now famous for tteokbokki."
     :culture/url "https://en.wikipedia.org/wiki/Tteok-bokki"
     :culture/url-provenance :wikipedia-en
     :culture/retrieved-at "2026-07-17"}
    {:culture/id "seoul.dish.jokbal"
     :culture/name "Jokbal"
     :culture/name-local "족발"
     :culture/municipality "seoul"
     :culture/country "KOR"
     :culture/kind :dish
     :culture/summary "Korean pig's-trotters dish; the current form started in the 1960s in Seoul's Jangchung-dong, still known for its long-established jokbal restaurants."
     :culture/url "https://en.wikipedia.org/wiki/Jokbal"
     :culture/url-provenance :wikipedia-en
     :culture/retrieved-at "2026-07-17"}
    {:culture/id "seoul.dish.samgyetang"
     :culture/name "Samgyetang"
     :culture/name-local "삼계탕"
     :culture/municipality "seoul"
     :culture/country "KOR"
     :culture/kind :dish
     :culture/summary "Korean soup of a whole young chicken filled with garlic, rice, jujube and ginseng, eaten across South Korea (including Seoul) especially on the sambok days of summer."
     :culture/url "https://en.wikipedia.org/wiki/Samgyetang"
     :culture/url-provenance :wikipedia-en
     :culture/retrieved-at "2026-07-17"}
    {:culture/id "seoul.beverage.makgeolli"
     :culture/name "Makgeolli"
     :culture/name-local "막걸리"
     :culture/municipality "seoul"
     :culture/country "KOR"
     :culture/kind :beverage
     :culture/summary "Milky, lightly sparkling rice wine, Korea's oldest alcoholic beverage, national rather than Seoul-specific, with a 21st-century resurgence in urban areas."
     :culture/url "https://en.wikipedia.org/wiki/Makgeolli"
     :culture/url-provenance :wikipedia-en
     :culture/retrieved-at "2026-07-17"}
    {:culture/id "seoul.festival.yeondeunghoe"
     :culture/name "Yeondeunghoe"
     :culture/name-local "연등회"
     :culture/municipality "seoul"
     :culture/country "KOR"
     :culture/kind :festival
     :culture/summary "Korean lantern festival for Buddha's Birthday, UNESCO Intangible Cultural Heritage (2020), whose most prominent celebration is held at Jogyesa temple in Seoul's Jongno District."
     :culture/url "https://en.wikipedia.org/wiki/Yeondeunghoe"
     :culture/url-provenance :wikipedia-en
     :culture/retrieved-at "2026-07-17"}
    {:culture/id "seoul.heritage.gyeongbokgung"
     :culture/name "Gyeongbokgung"
     :culture/name-local "경복궁"
     :culture/municipality "seoul"
     :culture/country "KOR"
     :culture/kind :heritage
     :culture/summary "First royal palace of the Joseon dynasty, established in 1395 in Seoul and primary residence of Korean monarchs until the 1592 Imjin War."
     :culture/url "https://en.wikipedia.org/wiki/Gyeongbokgung"
     :culture/url-provenance :wikipedia-en
     :culture/retrieved-at "2026-07-17"}
    {:culture/id "seoul.heritage.changdeokgung"
     :culture/name "Changdeokgung"
     :culture/name-local "창덕궁"
     :culture/municipality "seoul"
     :culture/country "KOR"
     :culture/kind :heritage
     :culture/summary "Former royal palace in Seoul, made a UNESCO World Heritage Site in 1997 as an outstanding example of Far Eastern palace architecture and garden design."
     :culture/url "https://en.wikipedia.org/wiki/Changdeokgung"
     :culture/url-provenance :wikipedia-en
     :culture/retrieved-at "2026-07-17"}]})

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
      :note (str "cloud-itonami-municipality-kor-seoul culture catalog "
                 "(ADR-2607171400): " (count (get catalog "seoul"))
                 " Seoul entries, each with a fetched-and-read citation. "
                 "Extend `culture.facts/catalog`, never fabricate an id/url.")})))

(defn by-kind [muni kind]
  (filterv #(= (:culture/kind %) kind) (spec-basis muni)))
