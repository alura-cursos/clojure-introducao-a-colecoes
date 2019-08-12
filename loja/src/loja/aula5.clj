(ns loja.aula5
  (:require [loja.db :as l.db]
            [loja.logic :as l.logic]))

(defn gastou-bastante? [info-do-usuario]
  (> (:preco-total info-do-usuario) 500))

(let [pedidos (l.db/todos-os-pedidos)
      resumo (l.logic/resumo-por-usuario pedidos)]
  (println "keep" (keep gastou-bastante? resumo))
  (println "filter" (filter gastou-bastante? resumo)))



(println "tentando entender dentro do keep e do filter")

(defn gastou-bastante? [info-do-usuario]
  (println "gastou-bastante?" (:usuario-id info-do-usuario))
  (> (:preco-total info-do-usuario) 500))



(let [pedidos (l.db/todos-os-pedidos)
      resumo (l.logic/resumo-por-usuario pedidos)]
  (println "keep" (keep gastou-bastante? resumo)))


(println "Vamos isolar...")

(println (range 10))
(println (take 2 (range 10000000000000)))
; a sequência não esta sendo "gulosa" (eager)
(let [sequencia (range 1000000)]
  (println (take 2 sequencia))
  (println (take 2 sequencia)))                             ; imutabilidade, sequencia eh a mesma
; esta sendo LAZY (preguicoso)!!!!



(defn filtro1 [x]
  (println "filtro1" x)
  x)

(println (map filtro1 (range 10)))

(defn filtro2 [x]
  (println "filtro2" x)
  x)

(println (map filtro2 (map filtro1 (range 10))))

(->> (range 10)
     (map filtro1)
     (map filtro2)
     println)







; CHUNKS de 32
(->> (range 50)
     (map filtro1)
     (map filtro2)
     println)


(->> (range 50)
     (mapv filtro1)
     (mapv filtro2)
     println)


(->> [0 1 2 3 4 5 6 7 8 9 0 1 2 3 4 5 6 7 8 9 0 1 2 3 4 5 6 7 8 0 1 2 3 4 5 6 7 8 9 0]
     (map filtro1)
     (map filtro2)
     println)


; lista ligada foi 100% lazy nesse cenário
(->> '(0 1 2 3 4 5 6 7 8 9 0 1 2 3 4 5 6 7 8 9 0 1 2 3 4 5 6 7 8 0 1 2 3 4 5 6 7 8 9 0)
     (map filtro1)
     (map filtro2)
     println)











