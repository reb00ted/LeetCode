// https://leetcode.com/problems/find-all-possible-recipes-from-given-supplies/

class Solution {
    public List<String> findAllRecipes(String[] recipes, List<List<String>> ingredients, String[] supplies) {
        int n = recipes.length;

        // ingredient -> ingredient 가 필요한 recipe 리스트
        Map<String, List<String>> graph = new HashMap<>();

        // recipe -> recipe 를 완성하기 위해 필요한 재료들
        Map<String, Set<String>> need = new HashMap<>();

        // graph 와 need 설정
        for (int i = 0; i < n; i++) {
            need.put(recipes[i], new HashSet<>(ingredients.get(i)));
            for (String ingredient : ingredients.get(i)) {
                if (!graph.containsKey(ingredient)) {
                    graph.put(ingredient, new ArrayList<>());
                }
                graph.get(ingredient).add(recipes[i]);
            }
        }

        // 초기 제공되는 재료들로부터 탐색 시작
        List<String> made = new ArrayList<>();
        Queue<String> supply = new LinkedList<>();
        for (String s : supplies) {
            supply.add(s);
        }

        while (!supply.isEmpty()) {
            // 현재 재료
            String ingredient = supply.poll();
            if (!graph.containsKey(ingredient)) continue;

            // 현재 재료가 필요한 레시피들 탐색
            for (String recipe : graph.get(ingredient)) {
                // 각각의 레시피의 필요한 재료에서 현재 재료 제거
                Set<String> needList = need.get(recipe);
                needList.remove(ingredient);

                // recipe 를 완성하기 위해 필요한 재료들이 더 이상 없다면 완성!
                if (needList.size() == 0) {
                    made.add(recipe);
                    supply.add(recipe);
                }
            }
        }
        return made;
    }
}